package be.howest.twentytwo.parametergame.desktop;

import com.google.inject.AbstractModule;

import be.howest.twentytwo.parametergame.service.db.IDataService;
import be.howest.twentytwo.parametergame.service.db.InMemoryDataService;
import be.howest.twentytwo.parametergame.service.db.SQLDataService;
import be.howest.twentytwo.parametergame.service.file.IFileAccessor;
import be.howest.twentytwo.parametergame.service.file.InMemoryFileAccessor;
import be.howest.twentytwo.parametergame.service.file.POJOFileAccessor;
import be.howest.twentytwo.parametergame.service.platform.DesktopService;
import be.howest.twentytwo.parametergame.service.platform.IPlatformService;

public class DesktopModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(IPlatformService.class).to(DesktopService.class);
		bind(IDataService.class).to(InMemoryDataService.class);
		// bind(IFileAccessor.class).to(InMemoryFileAccessor.class);
		bind(IFileAccessor.class).to(POJOFileAccessor.class);
	}

}
